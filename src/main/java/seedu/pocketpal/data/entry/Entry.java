package seedu.pocketpal.data.entry;

import seedu.pocketpal.communication.Serialisable;
import seedu.pocketpal.data.parsing.EntryParser;
import seedu.pocketpal.frontend.constants.EntryConstants;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Represents an entry, from which various possible entry types
 * such as Food, Medical or Income are derived. User may specify
 * their desired entry type when adding to their entry log.
 * e.g., <code>/add lunch -p 15 -c food</code>
 */
public class Entry implements Serialisable {
    private Category category;
    private String description;
    private double amount;
    private final String dateTime;
    private final UUID uuid;

    public Entry(String description, double amount, Category category) {
        assert amount >= 0 : "Entry amount must be non-negative!";
        assert category != null : "Entry category cannot be null";
        assert !description.isEmpty() : "Entry description cannot be empty";
        this.description = description;
        this.amount = amount;
        this.category = category;
        LocalDateTime dateTime = LocalDateTime.now();
        this.dateTime = dateTime.format(DateTimeFormatter.ofPattern("d MMM uuuu, HH:mm:ss"));
        this.uuid = UUID.randomUUID();
    }

    public double getAmount() {
        return amount;
    }

    public Category getCategory() {
        return category;
    }

    public String getDateTime(){
        return dateTime;
    }

    public String getCategoryString() {
        switch (category) {
        case CLOTHING:
            return EntryConstants.CLOTHING;
        case ENTERTAINMENT:
            return EntryConstants.ENTERTAINMENT;
        case FOOD:
            return EntryConstants.FOOD;
        case MEDICAL:
            return EntryConstants.MEDICAL;
        case OTHERS:
            return EntryConstants.OTHERS;
        case PERSONAL:
            return EntryConstants.PERSONAL;
        case UTILITIES:
            return EntryConstants.UTILITIES;
        case TRANSPORTATION:
            return EntryConstants.TRANSPORTATION;
        case INCOME:
            return EntryConstants.INCOME;
        default:
            throw new IllegalArgumentException();
        }
    }

    public String getDescription() {
        return description;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setAmount(double amount) {
        assert amount >= 0 : "Entry amount must be non-negative!";
        this.amount = amount;
    }

    public void setCategory(Category category) {
        assert category != null : "Entry category cannot be null";
        this.category = category;
    }

    public void setDescription(String description) {
        assert !description.isEmpty() : "Entry description cannot be empty";
        this.description = description;
    }

    @Override
    public String serialise() {
        return EntryParser.serialise(this);
    }

    /**
     * Compare to another entry. Result is independent of UUID.
     * @param entry Entry to compare
     * @return true if both entry is the same as this, false otherwise
     */
    public boolean equals(Entry entry) {
        boolean isSameAmount = amount == entry.getAmount();
        boolean isSameCategory = category == entry.getCategory();
        boolean isSameDescription = description.equals(entry.getDescription());
        boolean isSameDateTime = dateTime.compareTo(entry.getDateTime()) == 0;
        return isSameAmount && isSameCategory && isSameDescription && isSameDateTime;
    }
}
