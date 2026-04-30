

# PersonV1

## oneOf schemas
* [PersonLegalV1](PersonLegalV1.md)
* [PersonNaturalV1](PersonNaturalV1.md)

## Example
```java
// Import classes:
import com.definancy.model.PersonV1;
import com.definancy.model.PersonLegalV1;
import com.definancy.model.PersonNaturalV1;

public class Example {
    public static void main(String[] args) {
        PersonV1 examplePersonV1 = new PersonV1();

        // create a new PersonLegalV1
        PersonLegalV1 examplePersonLegalV1 = new PersonLegalV1();
        // set PersonV1 to PersonLegalV1
        examplePersonV1.setActualInstance(examplePersonLegalV1);
        // to get back the PersonLegalV1 set earlier
        PersonLegalV1 testPersonLegalV1 = (PersonLegalV1) examplePersonV1.getActualInstance();

        // create a new PersonNaturalV1
        PersonNaturalV1 examplePersonNaturalV1 = new PersonNaturalV1();
        // set PersonV1 to PersonNaturalV1
        examplePersonV1.setActualInstance(examplePersonNaturalV1);
        // to get back the PersonNaturalV1 set earlier
        PersonNaturalV1 testPersonNaturalV1 = (PersonNaturalV1) examplePersonV1.getActualInstance();
    }
}
```


