
package foo;

import javax.jws.WebService;

@WebService(endpointInterface = "foo.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    public String sayHi(String text) {
        return "Hello " + text;
    }
}

