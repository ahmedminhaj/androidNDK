LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := testndk
LOCAL_SRC_FILES := testndk.c

include $(BUILD_SHARED_LIBRARY)
