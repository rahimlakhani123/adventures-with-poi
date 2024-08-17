import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class PlayWithMethodHandle {
    public static void main(String[] args) throws Throwable {
        String className = "org.self.StringFormatter";
        String methodName = "format";

        // Load the class
        Class<?> clazz = Class.forName(className);
        MethodType methodType = MethodType.methodType(String.class, String.class);

        // Create a MethodHandle for the static method
        MethodHandle methodHandle = MethodHandles.lookup()
                .findStatic(clazz, methodName, methodType);

        var output = methodHandle.invoke("Hello");

        System.out.println(output);
    }
}
