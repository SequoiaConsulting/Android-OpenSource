# JavascriptWebviewDemo

This is an application demonstrating how to achieve two-way communication between WebApp and Native.

Scenarios covered - 

1. WebApp to Native
    We have a button in webview onclick of which a call to action is sent to Activity via JavascriptInterface.
    The call to action can render a new url in webview or call a script function of html.

2. Native to WebApp 
    The native view has fields to enter data and a button to send that data to webview via call to it's script function. The script function manipulates the        received data and displays it accordingly.
    The script function can also return a value and that will be received in the callback of the calling script function.

# Requirements

 * Mid sdk version 21
 * Build project sing Android Studio

# Documentation

You can visit the blog explaining about this here-

https://medium.com/sequoia-com/leveraging-the-power-of-webapps-in-native-android

# Language

Kotlin



