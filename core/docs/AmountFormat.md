

# AmountFormat

Precise representation of a amount combining a raw value with decimal precision information. This structure ensures accurate handling of token amounts across  different assets with varying decimal places.

## oneOf schemas
* [AmountRaw](AmountRaw.md)
* [AmountValue](AmountValue.md)

## Example
```java
// Import classes:
import com.definancy.model.AmountFormat;
import com.definancy.model.AmountRaw;
import com.definancy.model.AmountValue;

public class Example {
    public static void main(String[] args) {
        AmountFormat exampleAmountFormat = new AmountFormat();

        // create a new AmountRaw
        AmountRaw exampleAmountRaw = new AmountRaw();
        // set AmountFormat to AmountRaw
        exampleAmountFormat.setActualInstance(exampleAmountRaw);
        // to get back the AmountRaw set earlier
        AmountRaw testAmountRaw = (AmountRaw) exampleAmountFormat.getActualInstance();

        // create a new AmountValue
        AmountValue exampleAmountValue = new AmountValue();
        // set AmountFormat to AmountValue
        exampleAmountFormat.setActualInstance(exampleAmountValue);
        // to get back the AmountValue set earlier
        AmountValue testAmountValue = (AmountValue) exampleAmountFormat.getActualInstance();
    }
}
```


