package utils;

public class TestContext {

    // Use InheritableThreadLocal so child threads can inherit the user if needed
    private static final ThreadLocal<String> currentUser = new InheritableThreadLocal<>();

    // Set the user for the current thread
    public static void setUser(String user)
    {
        currentUser.set(user);
    }

    // Get the user for the current thread
    public static String getUser()
    {
        String user = currentUser.get();
        return (user != null) ? user : null;
    }

    // Clear the user for the current thread
    public static void clear()
    {
        currentUser.remove();
    }
}
