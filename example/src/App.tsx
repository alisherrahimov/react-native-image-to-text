import * as React from 'react';

import { Button, Platform, StyleSheet, View } from 'react-native';
import { imageToText } from 'react-native-image-to-text';
import {
  Camera,
  useCameraDevice,
  useCameraPermission,
} from 'react-native-vision-camera';
export default function App() {
  const refCamera = React.useRef<Camera>(null);
  const device = useCameraDevice('back');
  const { hasPermission, requestPermission } = useCameraPermission();

  const onTake = React.useCallback(async () => {
    const image = await refCamera.current?.takePhoto({
      qualityPrioritization: 'quality',
    });
    imageToText(
      Platform.OS === 'android' ? `file://${image?.path!}` : image?.path!
    )
      .then((res) => {
        console.log(res);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);

  React.useEffect(() => {
    requestPermission();
  }, [requestPermission]);

  if (!device || !hasPermission) {
    return null;
  }
  return (
    <View style={styles.container}>
      <Camera
        ref={refCamera}
        device={device}
        isActive={true}
        photo={true}
        style={styles.camera}
      />
      <Button title="Take image" onPress={onTake} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
  camera: {
    width: '100%',
    height: 300,
  },
});
