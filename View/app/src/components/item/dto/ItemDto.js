export default class Item {
    constructor(id, price, title, description, categoryId, itemDetailId, createDate) {
        this.id = id;
        this.price = price;
        this.title = title,
        this.description = description,
        this.categoryId = categoryId,
        this.itemDetailId = itemDetailId,
        this.createDate = createDate
    }
}