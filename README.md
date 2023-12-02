# react-native-image-to-text

image to text

## Installation

```sh
npm install react-native-image-to-text
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
