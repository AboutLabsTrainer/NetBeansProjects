/*#define __int64 long long */

#include <jni.h>
#include "com_tutego_insel_jni_StrLen.h"
#include <stdio.h>
#include <string.h>

JNIEXPORT jint JNICALL Java_com_tutego_insel_jni_StrLen_strlen( JNIEnv *env, jclass clazz, jstring s )
{
  printf( "Hallo Java-Freunde!\n" );
  return 0;
}
