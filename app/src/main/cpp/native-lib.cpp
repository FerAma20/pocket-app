#include <jni.h>

extern "C"
JNIEXPORT jint JNICALL
Java_com_example_mypocketapp_ui_MainViewModel_sum(JNIEnv* env, jobject thiz, jint a, jint b) {
    return a + b;
}