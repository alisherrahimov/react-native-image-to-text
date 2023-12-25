# react-native-image-to-text

With this package you can read the text in the picture.
Support ANDROID & IOS

## Installation

```sh
npm install react-native-image-to-text
yarn add react-native-image-to-text
```


```
IOS //
pod install



ANDROID //
nothing
```


## Usage

```js
import { imageToText } from 'react-native-image-to-text';

// ...

try {
  const data = await imageToText(
    Platform.OS === 'ios' ? path : 'file://' + path
  );
} catch (error) {
  // throw error
}
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT

---

Made with [create-react-native-library](https://github.com/callstack/react-native-builder-bob)

___

Donations

https://www.buymeacoffee.com/alilion