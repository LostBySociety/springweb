package manager.Controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateProductPayload(
        @NotNull(message = "{catalogue.products.update.errors.tittle_is_null}")
        @Size(min = 3, max = 50, message = "{catalogue.products.update.errors.tittle_size_is_invalid}")
        String title,

        @Size(max = 1000, message = "{catalogue.products.update.errors.details_size_are_invalid}")
        String details){ }
