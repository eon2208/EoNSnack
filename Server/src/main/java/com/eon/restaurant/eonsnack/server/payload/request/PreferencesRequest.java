package com.eon.restaurant.eonsnack.server.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreferencesRequest {

        private Set<Integer> tagsId;

        private Set<Integer> cuisinesId;
}
