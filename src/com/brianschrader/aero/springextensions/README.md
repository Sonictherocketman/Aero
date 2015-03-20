Aero Spring Extensions
======================

Provided are a number of extensions for Spring Tool Suite (v4). The extensions have only been tested against STSv4.

Contents
--------

- LoginRequired Annotation: Use to mandate that a variable be in the session to allow access to certain functionality.
- SessionParam Annotation: Use in your method params to get values from the session.

How to use
----------

- LoginRequired

```java
    @LoginRequired  //<------
    @RequestMapping(value = "/someUrl", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView someMethod(HttpServletRequest request) {
        //Do stuff.
    }
```    

- SessionParam

```java
    @RequestMapping(value = "/someUrl", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView someMethod(HttpServletRequest request,
            @SessionParam(value="userId") Integer userId) {  //<------
        //Do stuff.
    }
```
    
How to Setup
------------

Add the following XML to your servlet-context.xml


- LoginRequired

```xml
    <!-- Allows for the loginRequired annotation. -->
    <beans:bean id="loginRequiredInterceptor" class="com.brianschrader.aero.springextensions.LoginRequiredInterceptor"/>    
    <interceptors>
        <beans:bean class="sdcounty.dcss.springextensions.LoginRequiredInterceptor" lazy-init="false"/>
    </interceptors>
```

- SessionParam

```xml
    <beans:bean id="sessionArgResolver" class="com.brianschrader.aero.springextensions.SessionParamArgumentResolver"/>
    <annotation-driven>
        <argument-resolvers>
            <beans:bean class="sdcounty.dcss.springextensions.SessionParamArgumentResolver" lazy-init="false"/>
        </argument-resolvers>
    </annotation-driven>
```
