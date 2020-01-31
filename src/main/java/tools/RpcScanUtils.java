package tools;

import com.google.common.collect.Sets;
import org.reflections.Reflections;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * 扫描工具类，使用org.reflections.reflections0.9.11框架
 */
public class RpcScanUtils {

    private RpcScanUtils() {
    }

    private static Set<Class<?>> beanContainer = Sets.newHashSet();

    /**
     * 扫描全部注解（包括接口和实现类）
     */
    private static Set<Class<?>> getAll(String packageName, Class t) {
        beanContainer.clear();
        beanContainer = new Reflections(packageName).getTypesAnnotatedWith(t);
        return beanContainer;
    }

    /**
     * 扫描带有注解的接口
     */
    public static Set<Class<?>> getInterfaces(String packageName, Class t) {
        return getAll(packageName, t).stream()
                .filter(Class::isInterface)
                .collect(Collectors.toSet());
    }

    /**
     * 扫描带有指定注解的bean
     */
    public static Set<Class<?>> getBeans(String packageName, Class t) {
        return getAll(packageName, t).stream()
                .filter(clazz -> !clazz.isInterface())
                .collect(Collectors.toSet());
    }
}