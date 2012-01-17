/*
 * Copyright 2012 Michael R. Lange <michael.r.lange@langmi.de>.
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
package de.langmi.javasnippets.spring.batch.processor;

import de.langmi.javasnippets.spring.batch.SimpleItem;
import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.test.MetaDataInstanceFactory;
import static org.junit.Assert.*;

/**
 * Some Tests for the AggregatingItemProcessor.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class AggregatingItemProcessorTest {

    private AggregatingItemsProcessor processor;

    @Test
    public void testProcessFirstCall() throws Exception {
        assertNull(processor.process(new SimpleItem(1, "1")));
    }

    @Test
    public void testProcessWithoutIdChange() throws Exception {
        assertNull(processor.process(new SimpleItem(1, "1")));
        assertNull(processor.process(new SimpleItem(1, "2")));
    }

    @Test
    public void testProcessWithIdChange() throws Exception {
        // first item
        SimpleItem firstItem = new SimpleItem(1, "1");
        assertNull(processor.process(firstItem));

        // second item, other id
        SimpleItem returnItem = processor.process(new SimpleItem(2, "2"));
        assertNotNull(returnItem);
        // should return first item
        assertEquals(firstItem, returnItem);
    }

    @Test
    public void testProcessWithIdAndValueChange() throws Exception {
        // first item
        SimpleItem firstItem = new SimpleItem(1, "1");
        assertNull(processor.process(firstItem));
        // second item, same id, other value
        SimpleItem secondItem = new SimpleItem(1, "2");
        assertNull(processor.process(secondItem));
        // third item, other id, value does not matter
        SimpleItem thirdItem = new SimpleItem(2, "2");
        SimpleItem returnItem = processor.process(thirdItem);
        assertNotNull(returnItem);
        // ids should match first/second item.id
        assertEquals(firstItem.getId(), returnItem.getId());
        assertEquals(secondItem.getId(), returnItem.getId());
        // should return first item with combined values
        String expectedValue = firstItem.getValue() + secondItem.getValue();
        assertEquals(expectedValue, returnItem.getValue());
        // and yet another process sequence, with another id change
        // last item before expected
        assertEquals(thirdItem, processor.process(new SimpleItem(3, "4")));
    }

    @Before
    public void before() throws Exception {
        processor = new AggregatingItemsProcessor();
        processor.beforeStep(MetaDataInstanceFactory.createStepExecution());
    }
}
