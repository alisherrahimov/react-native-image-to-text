import Foundation
import MLKitTextRecognition
import MLKitVision

@objc(ImageToText)
class ImageToText: NSObject {
    let latinOptions = TextRecognizerOptions()
    let latinTextRecognizer = TextRecognizer.textRecognizer()

    @objc(imageToText:withResolver:withRejecter:)
    func imageToText(path: String, resolve: RCTPromiseResolveBlock, reject: RCTPromiseRejectBlock) {
        var res = ""
        let image = UIImage(named: path)
        let visionImage = VisionImage(image: image!)
        latinTextRecognizer.process(visionImage) { text, error in
            guard error == nil, let text = text else {
                let errorString = error?.localizedDescription
                print("Text recognizer failed with error: \(String(describing: errorString))")
                return
            }
            // Blocks.
            for block in text.blocks {
                // Lines.
                for line in block.lines {
                    // This is your result
                    print(block.text, line.text)
                    res += block.text
                }
            }
        }
        resolve(res)
    }
}
