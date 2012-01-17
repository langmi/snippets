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
package de.langmi.javasnippets.spring.batch;

/**
 * Simple Domain Class for Spring Batch Examples.
 *
 * @author Michael R. Lange <michael.r.lange@langmi.de>
 */
public class SimpleItem {

    private int id;
    private int sharedId;
    private String value;

    public SimpleItem() {
    }

    public SimpleItem(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public SimpleItem(int id, int sharedId, String value) {
        this.id = id;
        this.sharedId = sharedId;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getSharedId() {
        return sharedId;
    }

    public void setSharedId(int sharedId) {
        this.sharedId = sharedId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SimpleItem other = (SimpleItem) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.sharedId != other.sharedId) {
            return false;
        }
        if ((this.value == null) ? (other.value != null) : !this.value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.id;
        hash = 17 * hash + this.sharedId;
        hash = 17 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }
}
