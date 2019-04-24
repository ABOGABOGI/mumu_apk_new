# I use ZSH, here is what I added to my .zshrc file (config file)
# at ~/.zshrc

# Have the adb accessible, by including it in the PATH
export PATH="/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Users/bowo/Library/Android/sdk/platform-tools/"

# Setup your Android SDK path in ANDROID_HOME variable
export ANDROID_HOME=/Users/bowo/Library/Android/sdk

# Setup aapt tool so it accessible using a single command
export AAPT="$ANDROID_HOME/build-tools/28.0.3/aapt"

echo $AAPT;

# Install APK to device
# Use as: apkinstall app-debug.apk
export apkinstall="adb devices | tail -n +2 | cut -sf 1 | xargs -I X adb -s X install -r $1"
# As an alternative to apkinstall, you can also do just ./gradlew installDebug

# Alias for building and installing the apk to connected device
# Run at the root of your project
# $ buildAndInstallApk
export buildAndInstallApk='./gradlew assembleDebug && apkinstall ./app/build/outputs/apk/debug/app-debug.apk'

# Launch your debug apk on your connected device
# Execute at the root of your android project
# Usage: launchDebugApk
export launchDebugApk="adb shell monkey -p `$AAPT dump badging ./app/build/outputs/apk/debug/app-debug.apk | grep -e 'package: name' | cut -d \' -f 2` 1"

# ------------- Single command to build+install+launch apk------------#
# Execute at the root of your android project
# Use as: buildInstallLaunchDebugApk
$buildAndInstallApk && $launchDebugApk

# Note: Here I am building, installing and launching the debug apk which is usually in the path: `./app/build/outputs/apk/debug/app-debug.apk`
# when this command is executed from the root of the project
# If you would like to install and run any other apk, simply replace the path for debug apk with path of your own apk
