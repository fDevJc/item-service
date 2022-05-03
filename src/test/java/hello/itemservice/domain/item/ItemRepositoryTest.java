package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemName", 1000, 2);

        //when
        Item savedItem = itemRepository.save(item);
        Item foundItem = itemRepository.findById(item.getId());
        //then
        assertThat(foundItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemName1", 1000, 1);
        Item item2 = new Item("itemName2", 2000, 2);

        itemRepository.save(item1);
        itemRepository.save(item2);
        //when
        List<Item> result = itemRepository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(item1, item2);

    }

    @Test
    void update() {
        //given
        Item item1 = new Item("itemName1", 1000, 1);

        Item savedItem = itemRepository.save(item1);
        Long itemId = savedItem.getId();
        //when
        Item updateParam = new Item("itemName2", 2000, 2);
        itemRepository.update(itemId, updateParam);


        //then
        Item foundItem = itemRepository.findById(itemId);
        assertThat(foundItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(foundItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(foundItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}