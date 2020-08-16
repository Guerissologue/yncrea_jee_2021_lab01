# Tests Description
There are 2 kinds of tests :
* Structure tests that check the classes you create are in the correct package, have the correct methods, attributes, annotations and so on
* Behaviour tests that check if the implementation of your methods is correct

You will find in this document some tips in order to understand what we are trying to test.

## `DrugsServletStructureTestCase`
 * `shouldExtendsHttpServlet`: checks that the class extends the correct superclass
 * `shouldHaveWebServletAnnotation`: checks that the class is correctly annotated
 * `shouldHaveInitMethod`: checks if the method `init` is present

## `DrugsServletBehaviourTestCase`
 * `shouldNotHaveDrugsBeforeInit`: checks the presence of an attribute named `drugs` which is a list of `Drug` objects. Before the call of the `init` method, this list should be null.
 * `shouldHaveDrugsAfterInit`: checks that after the call of the `init` method, the list contains 2 items
 * `shouldSetDrugsInRequestThenDispatch`: checks that with the call of the `doGet` method, the `setAttribute` method is called on the `request` parameter then the `getRequestDispatcher` method is called then `forward` method is called on the dispatcher we just got
 * `shouldSaveDrugThenRedirect`: checks that with the call of the `doPost` method, your implementation fetches the sent parameters, build a new Drug object which is added to the list then redirect the browser to the list of drugs.
 
 You will notice that the path of redirection starts with "contextPath". Please note that this prefix must be retrieved thanks to this call : `req.getServletContext().getContextPath()`
