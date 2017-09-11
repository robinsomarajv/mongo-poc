(function() {
    'use strict';
    
    angular.module('item-details')
        .factory('staticBarDefaultService', staticBarDefaultService);

    function staticBarDefaultService() {
        var api = {
            url: 'templates/item-details/static-bar-default.html',
            dataCompleteness: {
                data: null,
                calculate: calculateDataCompleteness
            },
            filterOptions: {
                groupName: null,
                attributeName: null,
                showAttributesWithError: false,
                showEmptyAttributes: false,
                showAttributesWithValue: false,
                showRequiredAttributes: false,
                showOptionalAttributes: false,
                showAllAttributes: false,
                filterAttributesWithError: filterAttributesWithError,
                filterEmptyAttributes: filterEmptyAttributes,
                filterAttributesWithValue: filterAttributesWithValue,
                filterRequiredAttributes: filterRequiredAttributes,
                filterOptionalAttributes: filterOptionalAttributes,
                removeAllFilters: removeAllFilters,
                applyCustomFilter: applyCustomFilter,
                onCustomFilter: null
            },
            showAttributesWithError: null,
            showEmptyAttributes: null,
            showAttributesWithValue: null,
            showRequiredAttributes: null,
            showOptionalAttributes: null,
            showAllAttributes: null
        }
        
        return api;

        function applyCustomFilter() {
            if(api.filterOptions.showAttributesWithError) {api.filterOptions.showAttributesWithError = false}
            if(api.filterOptions.showEmptyAttributes) {api.filterOptions.showEmptyAttributes = false}
            if(api.filterOptions.showRequiredAttributes) {api.filterOptions.showRequiredAttributes = false}
            if(api.filterOptions.showOptionalAttributes) {api.filterOptions.showOptionalAttributes = false}

            if(api.filterOptions.onCustomFilter) {
                api.filterOptions.onCustomFilter(api.filterOptions.groupName, api.filterOptions.attributeName);
            }
        }

        function calculateDataCompleteness(data) {
            api.dataCompleteness.data = {};
            angular.forEach(data, function(groupDetails, group){
                var count = 0;
                var totalAttributes = 0;
                angular.forEach(groupDetails.attributes, function(attribute){
                    if(attribute.values && attribute.values.valuesList && attribute.values.valuesList.length !== 0) {
                        count++;
                    }
                    totalAttributes++;
                });

                api.dataCompleteness.data[group] = Math.round((count/totalAttributes) * 100);
            });
        }

        function filterAttributesWithError() {
            if(api.filterOptions.showAttributesWithError) {
                if(api.filterOptions.showEmptyAttributes) {api.filterOptions.showEmptyAttributes = false}
                if(api.filterOptions.showRequiredAttributes) {api.filterOptions.showRequiredAttributes = false}
                if(api.filterOptions.showOptionalAttributes) {api.filterOptions.showOptionalAttributes = false}
                if(api.filterOptions.showAttributesWithValue) {api.filterOptions.showAttributesWithValue = false}

                if(api.showAttributesWithError) {
                    api.showAttributesWithError();
                }
            }
            else {
                if(api.showAllAttributes) {
                    api.showAllAttributes();
                }
            }
        }

        function filterEmptyAttributes() {
            if(api.filterOptions.showEmptyAttributes) {
                if(api.filterOptions.showAttributesWithError) {api.filterOptions.showAttributesWithError = false}
                if(api.filterOptions.showRequiredAttributes) {api.filterOptions.showRequiredAttributes = false}
                if(api.filterOptions.showOptionalAttributes) {api.filterOptions.showOptionalAttributes = false}
                if(api.filterOptions.showAttributesWithValue) {api.filterOptions.showAttributesWithValue = false}

                if(api.showEmptyAttributes) {
                    api.showEmptyAttributes();
                }
            }
            else {
                if(api.showAllAttributes) {
                    api.showAllAttributes();
                }
            }
        }

        function filterAttributesWithValue() {
            if(api.filterOptions.showAttributesWithValue) {
                if(api.filterOptions.showAttributesWithError) {api.filterOptions.showAttributesWithError = false}
                if(api.filterOptions.showRequiredAttributes) {api.filterOptions.showRequiredAttributes = false}
                if(api.filterOptions.showOptionalAttributes) {api.filterOptions.showOptionalAttributes = false}
                if(api.filterOptions.showEmptyAttributes) {api.filterOptions.showEmptyAttributes = false}

                if(api.showAttributesWithValue) {
                    api.showAttributesWithValue();
                }
            }
            else {
                if(api.showAllAttributes) {
                    api.showAllAttributes();
                }
            }
        }

        function filterRequiredAttributes() {
            if(api.filterOptions.showRequiredAttributes) {
                if(api.filterOptions.showAttributesWithError) {api.filterOptions.showAttributesWithError = false}
                if(api.filterOptions.showEmptyAttributes) {api.filterOptions.showEmptyAttributes = false}
                if(api.filterOptions.showOptionalAttributes) {api.filterOptions.showOptionalAttributes = false}
                if(api.filterOptions.showAttributesWithValue) {api.filterOptions.showAttributesWithValue = false}

                if(api.showRequiredAttributes) {
                    api.showRequiredAttributes();
                }
            }
            else {
                if(api.showAllAttributes) {
                    api.showAllAttributes();
                }
            }
        }

        function filterOptionalAttributes() {
            if(api.filterOptions.showOptionalAttributes) {
                if(api.filterOptions.showAttributesWithError) {api.filterOptions.showAttributesWithError = false}
                if(api.filterOptions.showEmptyAttributes) {api.filterOptions.showEmptyAttributes = false}
                if(api.filterOptions.showRequiredAttributes) {api.filterOptions.showRequiredAttributes = false}

                if(api.showOptionalAttributes) {
                    api.showOptionalAttributes();
                }
            }
            else {
                if(api.showAllAttributes) {
                    api.showAllAttributes();
                }
            }
        }

        function removeAllFilters() {
        }
    }
})();