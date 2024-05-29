import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
   @UIApplicationDelegateAdaptor(AppDelegate.self) var appDelegate

   	var body: some Scene {
		WindowGroup {
			ContentView()
                .edgesIgnoringSafeArea(.all)
		}
	}
}

class AppDelegate: UIResponder, UIApplicationDelegate {
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        // Override point for customization
        SharedModulesKt.doInitKoin()
        return true
    }
}
