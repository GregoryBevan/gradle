/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.tasks.diagnostics.internal.insight

import org.gradle.api.artifacts.result.ComponentSelectionReason
import spock.lang.Specification
import spock.lang.Unroll

class DescribableComponentSelectionReasonTest extends Specification {
    @Unroll
    def "describes reason (#forced, #conflictResolution, #selectedByRule, #description)"() {
        expect:
        TestableComponentSelectionReason reason = new TestableComponentSelectionReason(forced: forced, conflictResolution: conflictResolution, selectedByRule: selectedByRule, description: description)
        new DescribableComponentSelectionReason(reason).describe() == describedReason

        where:
        forced | conflictResolution | selectedByRule | description | describedReason
        false  | false              | false          | 'failed'    | null
        true   | false              | false          | 'failed'    | 'failed'
        false  | true               | false          | 'failed'    | 'failed'
        false  | false              | true           | 'failed'    | 'failed'
        true   | true               | true           | 'failed'    | 'failed'
    }

    private class TestableComponentSelectionReason implements ComponentSelectionReason {
        boolean forced
        boolean conflictResolution
        boolean selectedByRule
        String description

        @Override
        String toString() {
            description
        }
    }
}
