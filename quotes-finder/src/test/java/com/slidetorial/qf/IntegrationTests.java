/**
 *
 */
package com.slidetorial.qf;

import org.junit.runner.RunWith;
import com.googlecode.junittoolbox.IncludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

/**
 * A suite to run integration tests. Integration test is a test which belongs to
 * {@link IntegrationTestMarker} category.
 *
 * @author goobar
 *
 */
@RunWith(WildcardPatternSuite.class)
@IncludeCategories(value = IntegrationTestMarker.class)
@SuiteClasses(value = { "**/**Test.class" })
public class IntegrationTests
{

}
