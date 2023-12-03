import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-image-to-text' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const ImageToText = NativeModules.ImageToText
  ? NativeModules.ImageToText
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

interface ImageToTextProps {
  blockText?: string;
  lineText?: string;
  elementText?: string;
  symboleText?: string;
}

export function imageToText(path: string): Promise<ImageToTextProps> {
  return ImageToText.imageToText(path);
}
