
#include <jni.h>
#include <stdio.h>
#include "com_cacard_jni_EchoString.h"


JNIEXPORT jstring JNICALL Java_com_cacard_jni_EchoString_Echo
(JNIEnv * env, jobject obj, jstring str)
  {
	// 1 just return 
	return str;//the return string type is jstring ,so can return
	
	// 2 jstring -> CString -> new jString -> return
	// TODO
  }

