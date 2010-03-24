/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.activemq.usecases;

import java.io.Serializable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectStreamException;

public class MyObject implements Serializable {

    private String message;
    private boolean writeObjectCalled, readObjectCalled, readObjectNoDataCalled;

    public MyObject(String message) {
        this.setMessage(message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        writeObjectCalled = true;
        Thread.dumpStack();
        out.defaultWriteObject();
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        readObjectCalled = true;
        Thread.dumpStack();
        in.defaultReadObject();
    }

    private void readObjectNoData() throws ObjectStreamException {
        Thread.dumpStack();
        readObjectNoDataCalled = true;
    }

    public boolean getWriteObjectCalled() {
        return writeObjectCalled;
    }

    public boolean getReadObjectCalled() {
        return readObjectCalled;
    }

    public boolean getReadObjectNoDataCalled() {
        return readObjectNoDataCalled;
    }
}


