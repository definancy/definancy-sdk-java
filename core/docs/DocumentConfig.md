

# DocumentConfig

## oneOf schemas
* [DocumentConfigCustodialV1](DocumentConfigCustodialV1.md)
* [DocumentConfigNonCustodialV1](DocumentConfigNonCustodialV1.md)
* [DocumentConfigPersonV1](DocumentConfigPersonV1.md)

## Example
```java
// Import classes:
import com.definancy.model.DocumentConfig;
import com.definancy.model.DocumentConfigCustodialV1;
import com.definancy.model.DocumentConfigNonCustodialV1;
import com.definancy.model.DocumentConfigPersonV1;

public class Example {
    public static void main(String[] args) {
        DocumentConfig exampleDocumentConfig = new DocumentConfig();

        // create a new DocumentConfigCustodialV1
        DocumentConfigCustodialV1 exampleDocumentConfigCustodialV1 = new DocumentConfigCustodialV1();
        // set DocumentConfig to DocumentConfigCustodialV1
        exampleDocumentConfig.setActualInstance(exampleDocumentConfigCustodialV1);
        // to get back the DocumentConfigCustodialV1 set earlier
        DocumentConfigCustodialV1 testDocumentConfigCustodialV1 = (DocumentConfigCustodialV1) exampleDocumentConfig.getActualInstance();

        // create a new DocumentConfigNonCustodialV1
        DocumentConfigNonCustodialV1 exampleDocumentConfigNonCustodialV1 = new DocumentConfigNonCustodialV1();
        // set DocumentConfig to DocumentConfigNonCustodialV1
        exampleDocumentConfig.setActualInstance(exampleDocumentConfigNonCustodialV1);
        // to get back the DocumentConfigNonCustodialV1 set earlier
        DocumentConfigNonCustodialV1 testDocumentConfigNonCustodialV1 = (DocumentConfigNonCustodialV1) exampleDocumentConfig.getActualInstance();

        // create a new DocumentConfigPersonV1
        DocumentConfigPersonV1 exampleDocumentConfigPersonV1 = new DocumentConfigPersonV1();
        // set DocumentConfig to DocumentConfigPersonV1
        exampleDocumentConfig.setActualInstance(exampleDocumentConfigPersonV1);
        // to get back the DocumentConfigPersonV1 set earlier
        DocumentConfigPersonV1 testDocumentConfigPersonV1 = (DocumentConfigPersonV1) exampleDocumentConfig.getActualInstance();
    }
}
```


