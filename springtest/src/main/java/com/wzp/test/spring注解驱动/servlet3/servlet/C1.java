package com.wzp.test.spring注解驱动.servlet3.servlet;

import java.io.IOException;
import java.net.*;
import java.nio.channels.Channel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

/**
 * @author
 * @date 2022 年 04 月 07 日
 */
public final class C1 extends ServerSocketChannel {
    /**
     * Initializes a new instance of this class.
     *
     * @param provider The provider that created this channel
     */
    protected C1(SelectorProvider provider) {
        super(provider);
    }


    @Override
    public ServerSocketChannel bind(SocketAddress local, int backlog) throws IOException {
        return null;
    }


    @Override
    public <T> ServerSocketChannel setOption(SocketOption<T> name, T value) throws IOException {
        return null;
    }

  
    @Override
    public <T> T getOption(SocketOption<T> name) throws IOException {
        return null;
    }

    /**
     * Returns a set of the socket options supported by this channel.
     *
     * <p> This method will continue to return the set of options even after the
     * channel has been closed.
     *
     * @return A set of the socket options supported by this channel
     */
    @Override
    public Set<SocketOption<?>> supportedOptions() {
        return null;
    }

    /**
     * Retrieves a server socket associated with this channel.
     *
     * <p> The returned object will not declare any public methods that are not
     * declared in the {@link ServerSocket} class.  </p>
     *
     * @return A server socket associated with this channel
     */
    @Override
    public ServerSocket socket() {
        return null;
    }

    /**
     * Accepts a connection made to this channel's socket.
     *
     * <p> If this channel is in non-blocking mode then this method will
     * immediately return <tt>null</tt> if there are no pending connections.
     * Otherwise it will block indefinitely until a new connection is available
     * or an I/O error occurs.
     *
     * <p> The socket channel returned by this method, if any, will be in
     * blocking mode regardless of the blocking mode of this channel.
     *
     * <p> This method performs exactly the same security checks as the {@link
     * ServerSocket#accept accept} method of the {@link
     * ServerSocket} class.  That is, if a security manager has been
     * installed then for each new connection this method verifies that the
     * address and port number of the connection's remote endpoint are
     * permitted by the security manager's {@link
     * SecurityManager#checkAccept checkAccept} method.  </p>
     *
     * @return The socket channel for the new connection,
     * or <tt>null</tt> if this channel is in non-blocking mode
     * and no connection is available to be accepted
     * @throws ClosedChannelException     If this channel is closed
     * @throws AsynchronousCloseException If another thread closes this channel
     *                                    while the accept operation is in progress
     * @throws ClosedByInterruptException If another thread interrupts the current thread
     *                                    while the accept operation is in progress, thereby
     *                                    closing the channel and setting the current thread's
     *                                    interrupt status
     * @throws NotYetBoundException       If this channel's socket has not yet been bound
     * @throws SecurityException          If a security manager has been installed
     *                                    and it does not permit access to the remote endpoint
     *                                    of the new connection
     * @throws IOException                If some other I/O error occurs
     */
    @Override
    public SocketChannel accept() throws IOException {
        return null;
    }

    /**
     * {@inheritDoc}
     * <p>
     * If there is a security manager set, its {@code checkConnect} method is
     * called with the local address and {@code -1} as its arguments to see
     * if the operation is allowed. If the operation is not allowed,
     * a {@code SocketAddress} representing the
     * {@link InetAddress#getLoopbackAddress loopback} address and the
     * local port of the channel's socket is returned.
     *
     * @return The {@code SocketAddress} that the socket is bound to, or the
     * {@code SocketAddress} representing the loopback address if
     * denied by the security manager, or {@code null} if the
     * channel's socket is not bound
     * @throws ClosedChannelException {@inheritDoc}
     * @throws IOException            {@inheritDoc}
     */
    @Override
    public SocketAddress getLocalAddress() throws IOException {
        return null;
    }

    /**
     * Closes this selectable channel.
     *
     * <p> This method is invoked by the {@link Channel#close
     * close} method in order to perform the actual work of closing the
     * channel.  This method is only invoked if the channel has not yet been
     * closed, and it is never invoked more than once.
     *
     * <p> An implementation of this method must arrange for any other thread
     * that is blocked in an I/O operation upon this channel to return
     * immediately, either by throwing an exception or by returning normally.
     * </p>
     *
     * @throws IOException If an I/O error occurs
     */
    @Override
    protected void implCloseSelectableChannel() throws IOException {

    }

    /**
     * Adjusts this channel's blocking mode.
     *
     * <p> This method is invoked by the {@link #configureBlocking
     * configureBlocking} method in order to perform the actual work of
     * changing the blocking mode.  This method is only invoked if the new mode
     * is different from the current mode.  </p>
     *
     * @param block If <tt>true</tt> then this channel will be placed in
     *              blocking mode; if <tt>false</tt> then it will be placed
     *              non-blocking mode
     * @throws IOException If an I/O error occurs
     */
    @Override
    protected void implConfigureBlocking(boolean block) throws IOException {

    }
}
