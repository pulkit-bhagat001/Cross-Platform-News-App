
import SwiftUI
import shared  // Import your Kotlin Multiplatform shared module

@main
struct iOSApp: App {
    // Registers AppDelegate for handling lifecycle events
    @UIApplicationDelegateAdaptor(AppDelegate.self) var delegate

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}

class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {
       initKoin()  // Initialize Koin **once** when the app starts
        return true
    }
}