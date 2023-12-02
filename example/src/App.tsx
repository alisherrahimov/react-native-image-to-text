import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import { imageToText } from 'react-native-image-to-text';

export default function App() {
  React.useEffect(() => {
    imageToText('path')
      .then((res) => {
        console.log(res);
      })
      .catch((error) => {
        console.error(error);
      });
  }, []);
  return (
    <View style={styles.container}>{/* <Text>Result: {result}</Text> */}</View>
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
});
