object BuildPlugins {
    val gradle by lazy { "com.android.tools.build:gradle:${Versions.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}" }
    val hilt by lazy { "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltAndroidGradlePlugin}" }
}

object Libraries {
    val hiltCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltAndroid}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hiltAndroid}" }
    val retrofitConverterGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.converterGson}" }
    val retrofitLoggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.retrofitLoggingInterceptor}" }
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutinesAndroid}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val ui by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
    val materialCompose by lazy { "androidx.compose.material:material:${Versions.compose}" }
    val uiToolingPreview by lazy { "androidx.compose.material:material:${Versions.compose}" }
    val runtimeLivedata by lazy { "androidx.compose.runtime:runtime-livedata:${Versions.runtimeLivedata}" }
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}" }
    val lifecycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleKtx}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
    val lifecycleViewmodelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleKtx}" }
    val lifecycleLivedataKtx by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleKtx}" }
}

object TestLibraries {
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val mockk by lazy { "io.mockk:mockk:${Versions.mockk}" }
    val kluent by lazy { "org.amshove.kluent:kluent:${Versions.kluent}" }
    val robolectric by lazy { "org.robolectric:robolectric:${Versions.robolectric}" }
    val testRules by lazy { "androidx.test:rules:${Versions.testRules}" }
    val androidxJunit by lazy { "androidx.test.ext:junit:${Versions.androidxJunit}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espressoCore}" }
    val uiTestJunit by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose}" }
    val uiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }
}