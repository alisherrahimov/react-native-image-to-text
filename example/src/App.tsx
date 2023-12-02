import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { imageToText } from 'react-native-image-to-text';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();

  React.useEffect(() => {
    imageToText("").then((val)=>{
      // content://com.android.providers.downloads.documents/document/msf%3A1000000018
      console.log(val)
    }).catch((err)=>{
      console.error(err)
    })
  }, []);
  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
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
});
