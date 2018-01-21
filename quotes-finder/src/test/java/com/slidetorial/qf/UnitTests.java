/**
 *
 */
package com.slidetorial.qf;

import org.junit.runner.RunWith;
import com.googlecode.junittoolbox.ExcludeCategories;
import com.googlecode.junittoolbox.SuiteClasses;
import com.googlecode.junittoolbox.WildcardPatternSuite;

/**
 * A suite to run unit tests only. Unit test is a test which doesn't belong to
 * {@link IntegrationTestMarker} category.
 *
 * @author goobar
 *
 */
@RunWith(WildcardPatternSuite.class)
@ExcludeCategories({ IntegrationTestMarker.class })
@SuiteClasses(value = { "**/**Test.class" })
public class UnitTests
{

}
