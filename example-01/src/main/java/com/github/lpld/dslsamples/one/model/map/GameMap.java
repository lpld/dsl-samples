package com.github.lpld.dslsamples.one.model.map;

import com.github.lpld.dslsamples.one.model.events.Event;
import com.github.lpld.dslsamples.one.model.events.GameEventListener;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Game map which is represented as a rectangular field {@code xSize x ySize}.
 *
 * @author leopold
 * @since 12/29/13
 */
@Setter
@Getter
public class GameMap {
    private final int xSize;
    private final int ySize;
    private final MapItem[][] field;
    private final Map<String, MapItem> itemsById = new HashMap<>();
    private GameEventListener eventListener;

    public GameMap(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;

        field = new MapItem[xSize][ySize];
    }

    public void addItems(MapItem... items) {
        for (MapItem item : items) {
            addItem(item);
        }
    }

    public void addItem(MapItem mapItem) {
        int xCoord = mapItem.getLocation().getX();
        int yCoord = mapItem.getLocation().getY();

        field[xCoord][yCoord] = mapItem;
        if (!MapItem.NO_ID.equals(mapItem.getId())) {
            itemsById.put(mapItem.getId(), mapItem);
        }
        mapItem.setMap(this);
    }

    public MapItem itemByLocation(Location location) {
        return field[location.getX()][location.getY()];
    }

    public MapItem itemById(String id) {
        return itemsById.get(id);
    }

    public void removeItem(Location location) {
        removeItem(itemByLocation(location));
    }

    public void removeItem(MapItem item) {
        Location location = item.location;
        field[location.getX()][location.getY()] = null; //new EmptyItem(location);
        itemsById.remove(item.getId());
        // TODO unregister events for this item
    }

    public void publishEvent(Event event) {
        eventListener.handleEvent(event);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameMap gameMap = (GameMap) o;

        if (xSize != gameMap.xSize) return false;
        if (ySize != gameMap.ySize) return false;
        if (!Arrays.deepEquals(field, gameMap.field)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = xSize;
        result = 31 * result + ySize;
        return result;
    }
}
