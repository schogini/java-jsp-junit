/*
 * Copyright 2007 Lasse Koskela.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.sf.jsptest.acceptance.jsp;

import junit.framework.AssertionFailedError;
import net.sf.jsptest.HtmlTestCase;

/**
 * @author Lasse Koskela
 */
public class BasicJspTest extends HtmlTestCase {

    protected String getWebRoot() {
        return "src/main/webapp";
    }

    public void testRenderingTrivialJsp() throws Exception {
        get("/index.jsp");
        output().shouldContain("2017");
    }

    public void testOutputAssertion() throws Exception {
        get("/index.jsp");
        output().shouldContain("2019"); // Sree
        try {
            output().shouldContain("No such content");
            throw new RuntimeException("Assertion should've failed");
        } catch (AssertionFailedError expected) {
        }
    }

    // public void testRenderingSameJspMultipleTimes() throws Exception {
    //     for (int i = 0; i < 5; i++) {
    //         get("/index.jsp");
    //         output().shouldContain("Hello from Sree");
    //     }
    // }

    // public void testJspFileWithSpecialCharactersInName()
    //         throws Exception {
    //     get("/name$_test-file2.jsp");
    //     output().shouldContain("Hello from Sree");
    // }

    // public void testJspFileInSubDirectory() throws Exception {
    //     get("/sub/dir/page_in_subdir.jsp");
    //     output().shouldContain("Hello from Sree");
    // }

    // public void testJspFileInSubDirectoryWithSpecialCharsInPath()
    //         throws Exception {
    //     get("/su-b.dir/page_in_sub.dir.jsp");
    //     output().shouldContain("Hello from Sree");
    // }

    // public void testJspPathNotStartingWithSlash() throws Exception {
    //     try {
    //         get("sub/dir/page_in_subdir.jsp");
    //         fail("JSP path not starting with a slash should raise an exception");
    //     } catch (AssertionFailedError pass) {
    //         throw pass;
    //     } catch (Throwable e) {
    //         assertEquals(IllegalArgumentException.class, e.getClass());
    //     }
    // }
}
