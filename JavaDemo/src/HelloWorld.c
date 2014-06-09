

#include <jni.h>
#include <stdio.h>
#include "com_cacard_jni_HelloWorld.h"

JNIEXPORT void JNICALL Java_com_cacard_jni_HelloWorld_print
  (JNIEnv * env, jobject obj)
  {
	printf("Hello World Message from C language\n");
	return;
  }
  