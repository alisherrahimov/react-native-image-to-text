#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(ImageToText, NSObject)

RCT_EXTERN_METHOD(imageToText:(NSString)path
                 withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
