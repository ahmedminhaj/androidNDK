#include<jni.h>
#include<string.h>

jstring Java_com_example_gm1_testndk_helloWorld(JNIEnv* env, jobject obj){
    return  (*env)->NewStringUTF(env,"PeddleCloud.com");
}

