package com.volvo.emsp.common.enumcache;

import com.volvo.emsp.common.exception.BizException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 枚举缓存
 *
 * @author gaoyang
 * @since 2025-04-12 13:27
 */
public class EnumCache {

    /**
     * 以枚举值构建的缓存结构
     **/
    static final Map<Class<? extends Enum>, Map<Object, Enum>> CACHE_BY_CODE = new ConcurrentHashMap<>();

    /**
     * 以枚举描述构建的缓存结构
     **/
    static final Map<Class<? extends Enum>, Map<Object, Enum>> CACHE_BY_DESC = new ConcurrentHashMap<>();

    /**
     * 以枚举名称构建的缓存结构
     **/
    static final Map<Class<? extends Enum>, Map<Object, Enum>> CACHE_BY_ENUM_NAME = new ConcurrentHashMap<>();

    /**
     * 枚举静态块加载标识缓存结构
     */
    static final Map<Class<? extends Enum>, Boolean> LOADED = new ConcurrentHashMap<>();


    /**
     * 以枚举名称构建缓存，在枚举的静态块里面调用
     *
     * @param clazz Class
     * @param enums 枚举数组
     * @param <E>   枚举
     */
    public static <E extends Enum> void registerByEnumName(Class<E> clazz, E[] enums) {
        if (CACHE_BY_ENUM_NAME.containsKey(clazz)) {
            throw new BizException(String.format("枚举 %s 已经构建过 EnumName 缓存，不允许重复构建", clazz.getSimpleName()));
        }
        Map<Object, Enum> map = new ConcurrentHashMap<>();
        for (E e : enums) {
            map.put(e.name(), e);
        }
        CACHE_BY_ENUM_NAME.put(clazz, map);
    }

    /**
     * 以枚举转换出的编码构建缓存，在枚举的静态块里面调用
     *
     * @param clazz       Class
     * @param enums       枚举数组
     * @param enumMapping 枚举缓存映射器
     * @param <E>         枚举
     */
    public static <E extends Enum> void registerByCode(Class<E> clazz, E[] enums, EnumMapping<E> enumMapping) {
        if (CACHE_BY_CODE.containsKey(clazz)) {
            throw new BizException(String.format("枚举 %s 已经构建过 code 缓存，不允许重复构建", clazz.getSimpleName()));
        }
        Map<Object, Enum> map = new ConcurrentHashMap<>();
        for (E e : enums) {
            Object value = enumMapping.value(e);
            if (map.containsKey(value)) {
                throw new BizException(String.format("枚举 %s 存在相同的值%s映射同一个枚举 %s.%s", clazz.getSimpleName(), value, clazz.getSimpleName(), e));
            }
            map.put(value, e);
        }
        CACHE_BY_CODE.put(clazz, map);
    }

    /**
     * 以枚举转换出的任意值构建缓存，在枚举的静态块里面调用
     *
     * @param clazz       Class
     * @param enums       枚举数组
     * @param enumMapping 枚举缓存映射器
     * @param <E>         枚举
     */
    public static <E extends Enum> void registerByDesc(Class<E> clazz, E[] enums, EnumMapping<E> enumMapping) {
        if (CACHE_BY_DESC.containsKey(clazz)) {
            throw new BizException(String.format("枚举 %s 已经构建过 desc 缓存，不允许重复构建", clazz.getSimpleName()));
        }
        Map<Object, Enum> map = new ConcurrentHashMap<>();
        for (E e : enums) {
            Object value = enumMapping.value(e);
            if (map.containsKey(value)) {
                throw new BizException(String.format("枚举 %s 存在相同的值 %s 映射同一个枚举 %s.%s", clazz.getSimpleName(), value, clazz.getSimpleName(), e));
            }
            map.put(value, e);
        }
        CACHE_BY_DESC.put(clazz, map);
    }

    /**
     * 从以枚举名称构建的缓存中通过枚举名获取枚举
     *
     * @param clazz       Class
     * @param enumName    枚举名称
     * @param defaultEnum 默认枚举
     * @param <E>         枚举
     * @return Enum
     */
    public static <E extends Enum> E findByEnumName(Class<E> clazz, String enumName, E defaultEnum) {
        return find(clazz, enumName, CACHE_BY_ENUM_NAME, defaultEnum);
    }

    /**
     * 从以枚举转换值构建的缓存中通过枚举转换值获取枚举
     *
     * @param clazz       Class
     * @param code        编码
     * @param defaultEnum 默认枚举
     * @param <E>         枚举
     * @return Enum
     */
    public static <E extends Enum> E findByCode(Class<E> clazz, Object code, E defaultEnum) {
        return find(clazz, code, CACHE_BY_CODE, defaultEnum);
    }

    /**
     * 从以枚举描述构建的缓存中通过枚举转换值获取枚举
     *
     * @param clazz       Class
     * @param desc        描述
     * @param defaultEnum 默认枚举
     * @param <E>         枚举
     * @return Enum
     */
    public static <E extends Enum> E findByDesc(Class<E> clazz, Object desc, E defaultEnum) {
        return find(clazz, desc, CACHE_BY_DESC, defaultEnum);
    }

    private static <E extends Enum> E find(Class<E> clazz, Object obj, Map<Class<? extends Enum>, Map<Object, Enum>> cache, E defaultEnum) {
        Map<Object, Enum> map = null;
        if ((map = cache.get(clazz)) == null) {
            // 触发枚举静态块执行
            executeEnumStatic(clazz);
            // 执行枚举静态块后重新获取缓存
            map = cache.get(clazz);
        }
        if (map == null) {
            String msg = null;
            if (cache == CACHE_BY_ENUM_NAME) {
                msg = String.format(
                        "枚举%s还没有注册到枚举缓存中，请在 %s.static 代码块中加入如下代码 : EnumCache.registerByEnumName(%s.class, %s.values());",
                        clazz.getSimpleName(),
                        clazz.getSimpleName(),
                        clazz.getSimpleName(),
                        clazz.getSimpleName()
                );
            }
            if (cache == CACHE_BY_CODE) {
                msg = String.format(
                        "枚举%s还没有注册到枚举缓存中，请在 %s.static 代码块中加入如下代码 : EnumCache.registerByCode(%s.class, %s.values(), %s::getXxx);",
                        clazz.getSimpleName(),
                        clazz.getSimpleName(),
                        clazz.getSimpleName(),
                        clazz.getSimpleName(),
                        clazz.getSimpleName()
                );
            }
            if (cache == CACHE_BY_DESC) {
                msg = String.format(
                        "枚举%s还没有注册到枚举缓存中，请在 %s.static 代码块中加入如下代码 : EnumCache.registerByDesc(%s.class, %s.values(), %s::getXxx);",
                        clazz.getSimpleName(),
                        clazz.getSimpleName(),
                        clazz.getSimpleName(),
                        clazz.getSimpleName(),
                        clazz.getSimpleName()
                );
            }
            throw new BizException(msg);
        }
        if (obj == null) {
            return defaultEnum;
        }
        Enum result = map.get(obj);
        return result == null ? defaultEnum : (E) result;
    }

    private static <E extends Enum> void executeEnumStatic(Class<E> clazz) {
        if (!LOADED.containsKey(clazz)) {
            synchronized (EnumCache.class) {
                if (!LOADED.containsKey(clazz)) {
                    try {
                        // 目的是让枚举类的static块运行，static块没有执行完是会阻塞在此的
                        Class.forName(clazz.getName());
                        LOADED.put(clazz, true);
                    } catch (Exception e) {
                        throw new BizException(e);
                    }
                }
            }
        }
    }

    /**
     * 枚举缓存映射器函数式接口
     */
    @FunctionalInterface
    public interface EnumMapping<E extends Enum> {
        /**
         * 自定义映射器
         *
         * @param e 枚举
         * @return 映射关系，最终体现到缓存中
         */
        Object value(E e);
    }
}
