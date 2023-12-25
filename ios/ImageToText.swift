import Foundation
import MLKitTextRecognition
import MLKitVision

@objc(ImageToText)
class ImageToText: NSObject {
    let latinOptions = TextRecognizerOptions()
    let latinTextRecognizer = TextRecognizer.textRecognizer()

    @objc(imageToText:withResolver:withRejecter:)
    func imageToText(path: String, resolve: RCTPromiseResolveBlock, reject: RCTPromiseRejectBlock) {
        var blockText = ""
        var lineText = ""
        var elementText = ""
        var symboleText = ""
        let image = UIImage(named: path)
        let visionImage = VisionImage(image: image!)
        latinTextRecognizer.process(visionImage) { text, error in
            guard error == nil, let text = text else {
                let errorString = error?.localizedDescription
                print("Text recognizer failed with error: \(String(describing: errorString))")
                return
            }
            for block in text.blocks {
                blockText+=block.text
                for line in block.lines {
                    lineText+=line.text
                    for element in line.elements {
                        elementText+=element.text
                    }
                }
            }
        }
        resolve(["blockText": blockText, "lineText": lineText, "elementText": elementText, "symboleText": symboleText])
    }
}
