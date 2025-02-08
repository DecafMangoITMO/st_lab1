package task_3;

import java.util.Objects;

public class Discovery {

    private final String name;
    private final DiscoveryType type;

    public Discovery(String name, DiscoveryType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public DiscoveryType getType() {
        return type;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Discovery discovery = (Discovery) object;
        return Objects.equals(name, discovery.name) && type == discovery.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }
}
