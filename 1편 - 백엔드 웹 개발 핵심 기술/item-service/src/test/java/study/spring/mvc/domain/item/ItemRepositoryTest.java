package study.spring.mvc.domain.item;

import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {
    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void clear() {
        itemRepository.clear();
    }

    @Test
    void save() {
        // given
        Item item = new Item("itemA", 10000, 10);

        // when
        Item savedItem = itemRepository.save(item);

        // then
        Item foundItem = itemRepository.findById(item.getId());

        assertThat(foundItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);

        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> result = itemRepository.findAll();

        // then
        assertThat(result).hasSize(2);
        assertThat(result).contains(item1, item2);
    }

    @Test
    void updateItem() {
        // given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        // when
        ItemUpdateRequestDto updatedItem = new ItemUpdateRequestDto("item2", 20000, 30);
        itemRepository.update(itemId, updatedItem);

        // then
        Item foundItem = itemRepository.findById(itemId);

        assertThat(foundItem.getItemName()).isEqualTo(updatedItem.getItemName());
        assertThat(foundItem.getPrice()).isEqualTo(updatedItem.getPrice());
        assertThat(foundItem.getQuantity()).isEqualTo(updatedItem.getQuantity());
    }
}