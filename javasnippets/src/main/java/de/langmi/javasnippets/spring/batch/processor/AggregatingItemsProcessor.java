/**
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
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;

/**
 * Aggregates many items to one item, assumes the items are ordered after 
 * some algorithm - here it is an ID.
 * Example for http://stackoverflow.com/a/8896735/62201.
 * 
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class AggregatingItemsProcessor implements ItemProcessor<SimpleItem, SimpleItem>, StepExecutionListener {

    private SimpleItem currentItem;
    private StepExecution stepExecution;

    @Override
    public SimpleItem process(SimpleItem newItem) throws Exception {
        SimpleItem returnItem = null;
        
        if (currentItem == null) {
            currentItem = new SimpleItem(newItem.getId(), newItem.getValue());
        } else if (currentItem.getId() == newItem.getId()) {
            // aggregate somehow
            String value = currentItem.getValue() + newItem.getValue();
            currentItem.setValue(value);
        } else if (stepExecution.getExecutionContext().containsKey("readerExhausted")
                && (Boolean) stepExecution.getExecutionContext().get("readerExhausted")
                && currentItem != null) {
            returnItem = currentItem;
        } else {
            // "clone"/copy currentItem
            returnItem = new SimpleItem(currentItem.getId(), currentItem.getValue());
            // replace currentItem
            currentItem = newItem;
        }

        return returnItem;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.stepExecution = stepExecution;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return stepExecution.getExitStatus();
    }
}
