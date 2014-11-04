/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package org.apache.mina.core.future;

import org.apache.mina.core.session.IoSession;

/**
 * An {@link IoFuture} for asynchronous connect requests.
 *
 * <h3>Example</h3>
 * <pre>
 * IoConnector connector = ...;
 * ConnectFuture future = connector.connect(...);
 * future.await(); // Wait until the connection attempt is finished.
 * IoSession session = future.getSession();
 * session.write(...);
 * </pre>
 *
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 */
public interface ConnectFuture extends IoFuture {
    /**
     * Returns {@link IoSession} which is the result of connect operation.
     *
     * @return {@code true} if the connect operation is not finished yet
     */
    IoSession getSession();

    /**
     * Returns the cause of the connection failure.
     *
     * @return <tt>null</tt> if the connect operation is not finished yet,
     *         or if the connection attempt is successful, otherwise returns
     *         teh cause of the exception
     */
    Throwable getException();

    /**
     * @return {@code true} if the connect operation is finished successfully.
     */
    boolean isConnected();

    /**
     * @return {@code true} if the connect operation has been canceled by
     * {@link #cancel()} method.
     */
    boolean isCanceled();

    /**
     * Sets the newly connected session and notifies all threads waiting for
     * this future.  This method is invoked by MINA internally.  Please do not
     * call this method directly.
     * 
     * @param session The created session to store in the ConnectFuture insteance
     */
    void setSession(IoSession session);

    /**
     * Sets the exception caught due to connection failure and notifies all
     * threads waiting for this future.  This method is invoked by MINA
     * internally.  Please do not call this method directly.
     * 
     * @param exception The exception to store in the ConnectFuture instance
     */
    void setException(Throwable exception);

    /**
     * Cancels the connection attempt and notifies all threads waiting for
     * this future.
     */
    void cancel();

    /**
     * {@inheritDoc}
     */
    ConnectFuture await() throws InterruptedException;

    /**
     * {@inheritDoc}
     */
    ConnectFuture awaitUninterruptibly();

    /**
     * {@inheritDoc}
     */
    ConnectFuture addListener(IoFutureListener<?> listener);

    /**
     * {@inheritDoc}
     */
    ConnectFuture removeListener(IoFutureListener<?> listener);
}
