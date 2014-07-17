package com.example.bean;

import javax.annotation.Nonnull;
import java.util.Collection;

public class AppProperties {

    @Nonnull
    private final String name;

    @Nonnull
    private final String serverName;

    @Nonnull
    private final Collection<String> toAddresses;

    public AppProperties(@Nonnull String name, @Nonnull String serverName, @Nonnull Collection<String> toAddresses) {
        this.name = name;
        this.serverName = serverName;
        this.toAddresses = toAddresses;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public String getServerName() {
        return serverName;
    }

    @Nonnull
    public Collection<String> getToAddresses() {
        return toAddresses;
    }
}
