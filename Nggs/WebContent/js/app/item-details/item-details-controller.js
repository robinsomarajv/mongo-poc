(function() {
    'use strict';

    angular.module('item-details')
        .controller('itemDetailsController', itemDetailsController)
        .factory('itemDetailsService', itemDetailsService)
        .factory('itemDetailsDataService', itemDetailsDataService);

    itemDetailsController.$inject = ['$routeParams', 'itemDetailsService']
    function itemDetailsController($routeParams, itemDetailsService) {
        var idvm = this;

        idvm.prodID = $routeParams.prodID;
        idvm.tradeGtin = $routeParams.tId;
        idvm.itemNbr = $routeParams.itemNbr;
        idvm.context = itemDetailsService;
        idvm.context.getItemDetails(idvm.prodID);
        idvm.context.getTradeItemDetails(idvm.tradeGtin);
        idvm.context.getSupplyItemDetails(idvm.itemNbr);
    }

    itemDetailsService.$inject = ['$rootScope', '$mdSidenav', '$mdToast', 'itemDetailsDataService', 'staticBarService', 'dynamicBarService'];
    function itemDetailsService($rootScope, $mdSidenav, $mdToast, itemDetailsDataService, staticBarService, dynamicBarService) {
        var views = {
            productItem: "product",
            tradeItem: "trade",
            supplyItem: "supply",
            offerItem: "offer"
        };

        var displayModes = {
            fullScreen: {
                id: "fullScreen",
                attributeCardWidth: 20,
                attributeListWidth: 100
            },
            normal: {
                id: "normal",
                attributeCardWidth: 33,
                attributeListWidth: 75
            }
        };

        var api = {
            views: views,
            selectedView: null,
            displayModes: displayModes,
            selectedDisplayMode: null,
            userPreference: $rootScope.userPreference,
            staticBar: staticBarService,
            dynamicBar: dynamicBarService,
            data: null,
            getItemDetails: getItemDetails,
            getTradeItemDetails: getTradeItemDetails,
            getSupplyItemDetails:getSupplyItemDetails,
            attributesList: {
                selectedAttribute: null,
                multiSelectMode: false,
                view: {
                    showSettings: showAttributeListSettings,
                    setView: setAttributeListView,
                    setViewTab: setAttributeListViewTab
                }
            },
            filterOptions: {
                attributeFilterLabel: null,
                clearAttributeFilter: clearAttributeFilter
            },
            customView: {
                list: [],
                selectedView: null,
                itemAttributes: null,
                create: {
                    selectedGroup: null,
                    templateName: null,
                    selectedAttributes : [],
                    activeAttribute: null,
                    filter: {
                        attributeName: null,
                        groupName: null
                    },
                    template: {
                        groups: []
                    },
                    groupName: null,
                    addGroupToTemplate: addGroupToTemplate,
                    addGroupRow: addGroupRow,
                    deleteGroupRow: deleteGroupRow,
                    attributeSelected: attributeSelected,
                    removeGroupFromTemplate: removeGroupFromTemplate,
                    addAttributeToGroup: addAttributeToGroup,
                    addAttributeAsGroupToGroup: addAttributeAsGroupToGroup,
                    setActiveGroupRow: setActiveGroupRow,
                    removeAttributeFromGroup: removeAttributeFromGroup,
                    filterAttributes: filterAttributes,
                    onTitleSelected: onTitleSelected,
                    createCustomTemplate: createCustomTemplate,
                    cancelCustomTemplate: cancelCustomTemplate
                },
                setCustomTemplate: setCustomTemplate
            },
            showTemplateStaticBar: showTemplateStaticBar,
            onAttributeSelected: onAttributeSelected,
            showErrorStaticBar: showErrorStaticBar,
            showDynamicBar: showDynamicBar,
            openTradeItemHierarchyMenu: openTradeItemHierarchyMenu,
            setTradeItemHierarchyItem: setTradeItemHierarchyItem,
            showFullScreenDisplayMode: showFullScreenDisplayMode,
            showNormalDisplayMode: showNormalDisplayMode,
            showAttributeLineage: showAttributeLineage,
            showItemHistoryStaticBar: showItemHistoryStaticBar,
            createCustomView: createCustomView
        };

        var productAttributes = null;
        return api;

        function setCustomTemplate(template) {
            console.log(template);
            api.customView.selectedView = template.value;
            var tmpl = {
                "id": "custom-view",
                "name": "Custom View",
                "url": "templates/item-details/custom-view-template.html"
            }

            api.attributesList.view.setView(tmpl);
        }

        function createCustomTemplate() {
            api.customView.list.push({
                name: api.customView.create.templateName,
                value: api.customView.create.template
            });

            $mdSidenav('customView').toggle()
                .then(function(){
                });
        }

        function cancelCustomTemplate() {
            api.customView.list.push({
                name: api.customView.create.templateName,
                value: api.customView.create.template
            });

            $mdSidenav('customView').toggle()
                .then(function(){
                });
        }

        function attributeSelected(attribute) {

            if(attribute.selected) {
                var index = 0;
                attribute.selected = false;
                for(var i=0;i < api.customView.create.selectedAttributes.length; i++) {
                    if(attribute.metadata.id === api.customView.create.selectedAttributes[i].metadata.id) {
                        index = i;
                        break;
                    }
                }

                api.customView.create.selectedAttributes.splice(index, 1);
            }
            else {
                attribute.selected = true;
                api.customView.create.selectedAttributes.push(attribute);
            }
        }

        function addGroupRow(group) {
            if (!group.rows) {
                group.rows = [];
            }

            var count = group.rows.length;
            var row = {
                id: count,
                selected: true,
                attributes: []
            };

            if(group.selectedRow) {
                group.selectedRow.selected = false;
            }

            group.selectedRow = row;
            group.rows.push(row);
        }

        function setActiveGroupRow(row) {
            if(api.customView.create.selectedGroup) {
                api.customView.create.selectedGroup.selectedRow.selected = false;
            }

            row.selected = true;
            api.customView.create.selectedGroup.selectedRow = row;
        }

        function deleteGroupRow(group, row) {
            var index = 0;
            for(var i = 0; i < group.rows.length; i++) {
                if(group.rows[i].id === row.id) {
                    index = i;
                }
            }

            group.rows.splice(index, 1);
        }

        function addGroupToTemplate() {
            var group = {
                name: api.customView.create.groupName,
                selected: true
            };

            if(api.customView.create.selectedGroup) {
                api.customView.create.selectedGroup.selected = false;
                api.customView.create.selectedGroup.selectedRow.selected = false;
            }

            api.customView.create.selectedGroup = group;
            api.customView.create.template.groups.push(group);

            api.customView.create.groupName = null;
        }

        function removeGroupFromTemplate() {
        }

        function addAttributeToGroup() {
            var attribute = api.customView.create.selectedAttributes[0];
            api.customView.create.selectedGroup.selectedRow.attributes.push({
                group: attribute.metadata.attributeGroup,
                id: attribute.metadata.id,
                label: attribute.metadata.label,
                value: attribute.values ? attribute.values[0] : null,
                titleWidth: 50,
                valueWidth: 50
            });

            attribute.selected = false;
            api.customView.create.selectedAttributes = [];
        }
        
        function addAttributeAsGroupToGroup() {
        }

        function removeAttributeFromGroup() {

        }

        function onTitleSelected(attribute) {
            api.customView.create.activeAttribute = attribute;
        }

        function filterAttributes() {
            angular.forEach(api.customView.itemAttributes, function(groupDetails, groupName){
                if(api.customView.create.filter.groupName !== null && api.customView.create.filter.groupName !== '') {
                    if(groupName.toLowerCase().indexOf(api.customView.create.filter.groupName.toLowerCase()) !== -1) {
                        groupDetails.show = true;
                    }
                    else {
                        groupDetails.show = false;
                    }
                }
                else {
                    groupDetails.show = true;
                }

                if(groupDetails.show) {
                    var hasAttributeToShow = false;
                    angular.forEach(groupDetails.attributes, function(attribute){
                        if(api.customView.create.filter.attributeName !== null && api.customView.create.filter.attributeName !== '') {
                            if(attribute.metadata.label.toLowerCase().indexOf(api.customView.create.filter.attributeName.toLowerCase()) !== -1) {
                                attribute.show = true;
                                hasAttributeToShow = true;
                            }
                            else {
                                attribute.show = false;
                            }
                        }
                        else {
                            attribute.show = true;
                            hasAttributeToShow = true;
                        }
                    });

                    if(!hasAttributeToShow) {
                        groupDetails.show = false;
                    }
                }
            });
        }

        function createCustomView() {
            $mdSidenav('customView').toggle()
                .then(function(){
                    if($mdSidenav('customView').isOpen()) {
                        api.customView.itemAttributes = angular.copy(api.data);
                    }
                });
        }

        function showAttributeLineage() {
            document.querySelector('#lineage').innerHTML = '';
            $mdSidenav('lineageBar').toggle()
                .then(function(){
                    if($mdSidenav('lineageBar').isOpen()) {
                        drawLineage();
                    }
                });

        }

        function drawLineage() {
            var margin = {top: 20, right: 20, bottom: 20, left: 150},
                width = 960 - margin.right - margin.left,
                height = 800 - margin.top - margin.bottom;

            var i = 0,
                duration = 750,
                root = api.attributesList.selectedAttribute.lineage;

            var tree = d3.layout.tree()
                .size([height, width]);

            var diagonal = d3.svg.diagonal()
                .projection(function(d) { return [d.y, d.x]; });

            var svg = d3.select("#lineage").append("svg")
                .attr("width", width + margin.right + margin.left)
                .attr("height", height + margin.top + margin.bottom)
                .append("g")
                .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

            function collapse(d) {
                if (d.children) {
                    d._children = d.children;
                    d._children.forEach(collapse);
                    d.children = null;
                }
            }

            root.x0 = height / 2;
            root.y0 = 0;
            //root.children.forEach(collapse);
            update(root);

            d3.select(self.frameElement).style("height", "600px");

            function update(source) {

                // Compute the new tree layout.
                var nodes = tree.nodes(root).reverse(),
                    links = tree.links(nodes);

                // Normalize for fixed-depth.
                nodes.forEach(function(d) { d.y = d.depth * 180; });

                // Update the nodes…
                var node = svg.selectAll("g.node")
                    .data(nodes, function(d) { return d.id || (d.id = ++i); });

                // Enter any new nodes at the parent's previous position.
                var nodeEnter = node.enter().append("g")
                    .attr("class", "node")
                    .attr("transform", function(d) { return "translate(" + source.y0 + "," + source.x0 + ")"; })
                    .on("click", click);

                nodeEnter.append("circle")
                    .attr("r", 1e-6)
                    .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

                nodeEnter.append("text")
                    .attr("x", function(d) { return d.children || d._children ? -10 : 10; })
                    .attr("dy", ".35em")
                    .attr("text-anchor", function(d) { return d.children || d._children ? "end" : "start"; })
                    .text(function(d) { return d.name; })
                    .style("fill-opacity", 1e-6);

                // Transition nodes to their new position.
                var nodeUpdate = node.transition()
                    .duration(duration)
                    .attr("transform", function(d) { return "translate(" + d.y + "," + d.x + ")"; });

                nodeUpdate.select("circle")
                    .attr("r", 4.5)
                    .style("fill", function(d) { return d._children ? "lightsteelblue" : "#fff"; });

                nodeUpdate.select("text")
                    .style("fill-opacity", 1);

                // Transition exiting nodes to the parent's new position.
                var nodeExit = node.exit().transition()
                    .duration(duration)
                    .attr("transform", function(d) { return "translate(" + source.y + "," + source.x + ")"; })
                    .remove();

                nodeExit.select("circle")
                    .attr("r", 1e-6);

                nodeExit.select("text")
                    .style("fill-opacity", 1e-6);

                // Update the links…
                var link = svg.selectAll("path.link")
                    .data(links, function(d) { return d.target.id; });

                // Enter any new links at the parent's previous position.
                link.enter().insert("path", "g")
                    .attr("class", "link")
                    .attr("d", function(d) {
                        var o = {x: source.x0, y: source.y0};
                        return diagonal({source: o, target: o});
                    });

                // Transition links to their new position.
                link.transition()
                    .duration(duration)
                    .attr("d", diagonal);

                // Transition exiting nodes to the parent's new position.
                link.exit().transition()
                    .duration(duration)
                    .attr("d", function(d) {
                        var o = {x: source.x, y: source.y};
                        return diagonal({source: o, target: o});
                    })
                    .remove();

                // Stash the old positions for transition.
                nodes.forEach(function(d) {
                    d.x0 = d.x;
                    d.y0 = d.y;
                });
            }

            // Toggle children on click.
            function click(d) {
                if (d.children) {
                    d._children = d.children;
                    d.children = null;
                } else {
                    d.children = d._children;
                    d._children = null;
                }
                update(d);
            }
        }

        function showFullScreenDisplayMode() {
            api.selectedDisplayMode = api.displayModes.fullScreen;
        }

        function showNormalDisplayMode() {
            api.selectedDisplayMode = api.displayModes.normal;
        }

        function openTradeItemHierarchyMenu($mdOpenMenu, $event) {
            $mdOpenMenu($event);
        }

        function setTradeItemHierarchyItem(hierarchyItem) {
            api.dynamicBar.active.context.selectedTradeHierarchyItem = hierarchyItem;
        }
        
        function getItemDetails(gtin) {
            resetPage();
            itemDetailsDataService.getItemProductAttributes(gtin)
                .then(
                    function(data) {
                        productAttributes = angular.copy(data);
                        api.data = data;

                        console.log(api.data);
                        api.selectedDisplayMode = displayModes.normal;
                        api.staticBar.selected.dataCompleteness.calculate(api.data);
                        api.selectedView = api.views.productItem;

                        if(api.userPreference.itemDetails.attributesList.view.selectedTemplate.id === "tab" ||
                            api.userPreference.itemDetails.attributesList.view.selectedTemplate.id === "list-tab") {
                            setDefaultAttributeListTabView();
                        }
                    }
                );
        }
        function getTradeItemDetails(gtin) {
            resetPage();
            itemDetailsDataService.getTradeItemProductAttributes(gtin)
                .then(
                    function(data) {
                        productAttributes = angular.copy(data);
                        api.data = data;

                        console.log(api.data);
                        api.selectedDisplayMode = displayModes.normal;
                        api.staticBar.selected.dataCompleteness.calculate(api.data);
                        api.selectedView = api.views.productItem;

                        if(api.userPreference.tradeItemDetails.attributesList.view.selectedTemplate.id === "tab" ||
                            api.userPreference.tradeItemDetails.attributesList.view.selectedTemplate.id === "list-tab") {
                            setDefaultTradeAttributeListTabView();
                        }
                    }
                );
        }
        function getSupplyItemDetails(gtin) {
            resetPage();
            itemDetailsDataService.getSupplyItemProductAttributes(gtin)
                .then(
                    function(data) {
                        productAttributes = angular.copy(data);
                        api.data = data;

                        console.log(api.data);
                        api.selectedDisplayMode = displayModes.normal;
                        api.staticBar.selected.dataCompleteness.calculate(api.data);
                        api.selectedView = api.views.productItem;

                        if(api.userPreference.supplyItemDetails.attributesList.view.selectedTemplate.id === "tab" ||
                            api.userPreference.supplyItemDetails.attributesList.view.selectedTemplate.id === "list-tab") {
                        	setDefaultSupplyAttributeListTabView();
                        }
                    }
                );
        }
        function onCustomFilter(groupNameText, attributeNameText) {

            if((groupNameText === null || groupNameText === '') && (attributeNameText === null || attributeNameText === '')) {
                api.filterOptions.attributeFilterLabel = null;
            }
            else {
                api.filterOptions.attributeFilterLabel = "Showing custom search attribute(s)";
            }

            var activeGroup = null;
            angular.forEach(api.data, function(groupDetails, groupName){
                if(groupNameText !== null && groupNameText !== '') {
                    if(groupName.toLowerCase().indexOf(groupNameText.toLowerCase()) !== -1) {
                        groupDetails.show = true;
                    }
                    else {
                        groupDetails.show = false;
                    }
                }
                else {
                    groupDetails.show = true;
                }

                if(groupDetails.show) {
                    var hasAttributeToShow = false;
                    angular.forEach(groupDetails.attributes, function(attribute){
                        if(attributeNameText !== null && attributeNameText !== '') {
                            if(attribute.metadata.label.toLowerCase().indexOf(attributeNameText.toLowerCase()) !== -1) {
                                attribute.show = true;
                                hasAttributeToShow = true;
                            }
                            else {
                                attribute.show = false;
                            }
                        }
                        else {
                            attribute.show = true;
                            hasAttributeToShow = true;
                        }
                    });

                    if(!hasAttributeToShow) {
                        groupDetails.show = false;
                    }
                }

                if(!activeGroup && groupDetails.show) {
                    activeGroup = groupName;
                }
            });

            if(api.userPreference.itemDetails.attributesList.view.selectedTemplate.id === 'tab' || 
                api.userPreference.itemDetails.attributesList.view.selectedTemplate.id === 'list-tab') {
                setAttributeListViewTab(activeGroup, api.data[activeGroup]);
            }
        }
        
        function resetPage() {
            api.staticBar.showDefaultStaticBar();

            api.staticBar.selected.showAttributesWithError = showAttributesWithError;
            api.staticBar.selected.showEmptyAttributes = showEmptyAttributes;
            api.staticBar.selected.showRequiredAttributes = showRequiredAttributes;
            api.staticBar.selected.showOptionalAttributes = showOptionalAttributes;
            api.staticBar.selected.showAllAttributes = showAllAttributes;
            api.staticBar.selected.showAttributesWithValue = showAttributesWithValue;
            api.staticBar.selected.filterOptions.onCustomFilter = onCustomFilter;
        }
        
        function showAttributeListSettings($mdOpenMenu, event) {
            $mdOpenMenu(event);
        }

        function showDynamicBar() {
            $mdSidenav('dynamicBar').toggle()
                .then(function(){
                    if($mdSidenav('dynamicBar').isOpen()) {
                        if(!api.dynamicBar.onItemSelected) {
                            api.dynamicBar.onItemSelected = onDynamicBarItemSelected;
                            api.dynamicBar.navigateTo(api.dynamicBar.tabs[0]);
                        }
                    }
                });
        }

        function onDynamicBarItemSelected(tab) {
            if(tab === 'trade') {
                onTradeItemSelected();
            }
            else if(tab === 'supply') {
                onSupplyItemSelected();
            }
        }

        function onTradeItemSelected() {
            api.selectedView = api.views.tradeItem;

            var attributes = itemDetailsDataService.processData(api.dynamicBar.active.context.selectedTradeHierarchyItem.attributes);

            api.data = attributes;
            api.staticBar.selected.dataCompleteness.calculate(api.data);
        }

        function onSupplyItemSelected() {
            api.selectedView = api.views.supplyItem;

            var attributes = itemDetailsDataService.processData(api.dynamicBar.active.context.selectedItem.attributes);

            api.data = attributes;
            api.staticBar.selected.dataCompleteness.calculate(api.data);
        }

        function showTemplateStaticBar() {
            api.staticBar.showTemplateStaticBar();
            api.staticBar.selected.onTemplateCreation = onCreatingNewTemplate;
            api.staticBar.selected.applyTemplate = applyTemplate;
            api.staticBar.selected.onClose = closeTemplateBar;
        }

        function showErrorStaticBar() {
            api.staticBar.showErrorStaticBar();
            api.staticBar.selected.loadErrorsList(api.data);
            api.staticBar.selected.onClose = closeErrorBar;
        }

        function showItemHistoryStaticBar() {
            api.staticBar.showItemHistoryStaticBar();
            api.staticBar.selected.loadItemHistoryList();
            api.staticBar.selected.onClose = closeItemHistoryBar;
        }

        function onCreatingNewTemplate() {
            api.attributesList.multiSelectMode = true;

            if(api.attributesList.selectedAttribute) {
                delete api.attributesList.selectedAttribute.selected;
            }
        }

        function applyTemplate(templateName, template) {
            api.filterOptions.attributeFilterLabel = templateName + " template applied";

            var templateGroupKeys = Object.keys(template);
            var groupKeys = Object.keys(api.data);
            angular.forEach(groupKeys, function(groupKey) {
                if(templateGroupKeys.indexOf(groupKey) !== -1) {
                    var templateGroupAttributeKeys = Object.keys(template[groupKey].attributes);
                    var groupAttributeKeys = Object.keys(api.data[groupKey].attributes);

                    angular.forEach(groupAttributeKeys, function (attributeKey) {
                        if(templateGroupAttributeKeys.indexOf(attributeKey) !== -1) {
                            api.data[groupKey].attributes[attributeKey].show = true;
                        }
                        else {
                            api.data[groupKey].attributes[attributeKey].show = false;
                        }
                    });
                }
                else {
                    api.data[groupKey].show = false;
                }
            });

            var applyTemplate = $mdToast.simple()
                .textContent(templateName + ' template applied to current item')
                .position('bottom right')
                .hideDelay(5000);

            $mdToast.show(applyTemplate);
        }

        function closeTemplateBar() {
            api.staticBar.showDefaultStaticBar();
        }

        function closeErrorBar() {
            api.staticBar.showDefaultStaticBar();
        }

        function closeItemHistoryBar() {
            api.staticBar.showDefaultStaticBar();
        }
        
        function onAttributeSelected(attribute) {
            if(api.attributesList.multiSelectMode) {
                api.staticBar.selected.create.addToTemplate(attribute);
            }
            else {
                if(api.attributesList.selectedAttribute !== null) {
                    api.attributesList.selectedAttribute.selected = false;
                }

                attribute.selected = true;
                api.attributesList.selectedAttribute = attribute;
                api.staticBar.showMetadataStaticBar();
                if(!api.staticBar.selected.onClose) {api.staticBar.selected.onClose = closeAttributeMetadataBar}
            }
        }

        function closeAttributeMetadataBar() {
            api.staticBar.showDefaultStaticBar();
        }

        function setDefaultAttributeListTabView() {
            var keys = Object.keys(api.data);
            var defaultItem = {
                name: keys[0],
                groupDetails: api.data[keys[0]]
            };
            api.userPreference.itemDetails.attributesList.view.selectedTemplate.selectedTab = defaultItem;
        }
        
        function setDefaultTradeAttributeListTabView() {
            var keys = Object.keys(api.data);
            var defaultItem = {
                name: keys[0],
                groupDetails: api.data[keys[0]]
            };
            api.userPreference.tradeItemDetails.attributesList.view.selectedTemplate.selectedTab = defaultItem;
        }
        function setDefaultSupplyAttributeListTabView() {
            var keys = Object.keys(api.data);
            var defaultItem = {
                name: keys[0],
                groupDetails: api.data[keys[0]]
            };
            api.userPreference.supplyItemDetails.attributesList.view.selectedTemplate.selectedTab = defaultItem;
        }
        
        function setAttributeListView(template) {
            api.userPreference.itemDetails.attributesList.view.selectedTemplate = template;
            if(template.id === "tab" || template.id === "list-tab") {
                var keys = Object.keys(api.data);
                var defaultItem = {
                    name: keys[0],
                    groupDetails: api.data[keys[0]]
                };

                api.userPreference.itemDetails.attributesList.view.selectedTemplate.selectedTab = defaultItem;
            }
        }

        function setAttributeListViewTab(tabName, groupDetails) {
            var item = {
                name: tabName,
                groupDetails: groupDetails
            }

            api.userPreference.itemDetails.attributesList.view.selectedTemplate.selectedTab = item;
        }

        function showAttributesWithError() {
            api.filterOptions.attributeFilterLabel = "Showing attribute(s) with error";

            angular.forEach(api.data, function(groupDetails, group){
                var itemWithError = 0;
                angular.forEach(groupDetails.attributes, function(attribute){
                    if(attribute.errors) {
                        attribute.show = true;
                        itemWithError++;
                    }
                    else {
                        attribute.show = false;
                    }
                });

                if(itemWithError !== 0) {
                    api.data[group].show = true;
                }
                else {
                    api.data[group].show = false;
                }
            });
        }

        function showEmptyAttributes() {
            api.filterOptions.attributeFilterLabel = "Showing attribute(s) without value";

            angular.forEach(api.data, function(groupDetails, group){
                var emptyAttributes = 0;
                angular.forEach(groupDetails.attributes, function(attribute){
                    if(!attribute.values || !attribute.values || attribute.values.length === 0 || attribute.values[0] === '') {
                        attribute.show = true;
                        emptyAttributes++;
                    }
                    else {
                        attribute.show = false;
                    }
                });

                if(emptyAttributes !== 0) {
                    api.data[group].show = true;
                }
                else {
                    api.data[group].show = false;
                }
            });
        }

        function showAttributesWithValue() {
            api.filterOptions.attributeFilterLabel = "Showing attribute(s) with value";

            angular.forEach(api.data, function(groupDetails, group){
                var attributesWithValue = 0;
                angular.forEach(groupDetails.attributes, function(attribute){
                    if(attribute.values && attribute.values && attribute.values.length !== 0) {
                        attribute.show = true;
                        attributesWithValue++;
                    }

                    else {
                        attribute.show = false;
                    }
                });

                if(attributesWithValue !== 0) {
                    api.data[group].show = true;
                }
                else {
                    api.data[group].show = false;
                }
            });
        }

        function showRequiredAttributes() {
            api.filterOptions.attributeFilterLabel = "Showing required attribute(s)";

            angular.forEach(api.data, function(groupDetails, group){
                var requiredAttributes = 0;
                angular.forEach(groupDetails.attributes, function(attribute){
                    if(attribute.metadata.required) {
                        attribute.show = true;
                        requiredAttributes++;
                    }
                    else {
                        attribute.show = false;
                    }
                });

                if(requiredAttributes !== 0) {
                    api.data[group].show = true;
                }
                else {
                    api.data[group].show = false;
                }
            });
        }

        function showOptionalAttributes() {
            api.filterOptions.attributeFilterLabel = "Showing optional attribute(s)";

            angular.forEach(api.data, function(groupDetails, group){
                var optionalAttributes = 0;
                angular.forEach(groupDetails.attributes, function(attribute){
                    if(!attribute.metadata.required) {
                        attribute.show = true;
                        optionalAttributes++;
                    }
                    else {
                        attribute.show = false;
                    }
                });

                if(optionalAttributes !== 0) {
                    api.data[group].show = true;
                }
                else {
                    api.data[group].show = false;
                }
            });
        }

        function clearAttributeFilter() {
            if(api.staticBar.selected.filterOptions) {
                if(api.staticBar.selected.filterOptions.showAttributesWithError) {api.staticBar.selected.filterOptions.showAttributesWithError = false};
                if(api.staticBar.selected.filterOptions.showEmptyAttributes) {api.staticBar.selected.filterOptions.showEmptyAttributes = false};
                if(api.staticBar.selected.filterOptions.showRequiredAttributes) {api.staticBar.selected.filterOptions.showRequiredAttributes = false};
                if(api.staticBar.selected.filterOptions.showOptionalAttributes) {api.staticBar.selected.filterOptions.showOptionalAttributes = false};
                if(api.staticBar.selected.filterOptions.showAttributesWithValue) {api.staticBar.selected.filterOptions.showAttributesWithValue = false};
                api.staticBar.selected.filterOptions.groupName = null;
                api.staticBar.selected.filterOptions.attributeName = null;
            }

            showAllAttributes();
        }

        function showAllAttributes() {
            api.filterOptions.attributeFilterLabel = null;

            angular.forEach(api.data, function(groupDetails, group){
                angular.forEach(groupDetails.attributes, function(attribute){
                    attribute.show = true;
                });

                api.data[group].show = true;
            });
        }
    }

    itemDetailsDataService.$inject = ['$http'];
    function itemDetailsDataService($http) {
        var api = {
            getItemProductAttributes: getItemProductAttributes,
            getTradeItemProductAttributes: getTradeItemProductAttributes,
            getSupplyItemProductAttributes: getSupplyItemProductAttributes,
            processData: processData
        }

        return api;

        function getItemProductAttributes(prodID) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/product/'+prodID
            }).then(
                function(response) {
                    return processData(response.data);
                }
            )
        }
        
        function getTradeItemProductAttributes(gtin) {
        	//var pathParam = {"tradeItemGtin": gtin,"informationProviderId":informationProviderId,"informationProviderTypeCode":informationProviderTypeCode,"recipientGln":recipientGln,"targetMarketCode":targetMarketCode};
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/gtItem/'+gtin
            }).then(
                function(response) {
                    return processData(response.data);
                }
            )
        }
        function getSupplyItemProductAttributes(gtin) {
            return $http({
                method: 'GET',
                url: 'http://localhost:8080/litem/'+gtin
            }).then(
                function(response) {
                    return processData(response.data);
                }
            )
        }

        function processData(data) {
            
				var toJson = {};
              for(var i=0; i<data.length; i++) {

               var val = {};
               
               val['values'] = data[i].values;
               
               val['metadata'] = data[i].metadata;
			   val['errors'] = null;

                   var s = data[i].id;
   
               toJson[s] = val;
               }
           //console.log(toJson);
		   var returnObj = {};
		  // returnObj['attributes']=toJson;
		   console.log(toJson);
		   //attributes.push(toJson);
           /* for(var i in toJson) {
                returnObj.attributes[i] = toJson[i];
            }*/

            /*for(var i in data.attributes.Optional) {
                returnObj.attributes[i] = data.attributes.Optional[i];
            }*/

            returnObj = groupAttributes(toJson);
		

            return returnObj;
        }

        function groupAttributes(attributes) {
            var groupedAttributes = {};
            var groupName = null;

            angular.forEach(attributes, function(attribute, key) {
                if (attribute.metadata.attributeGroup){
                    groupName = attribute.metadata.attributeGroup;
                }
                else {
                    groupName = "General";
                    attribute.metadata.attributeGroup = groupName;
                }

                attribute.show = true;

                if(groupedAttributes[groupName]) {
                    groupedAttributes[groupName].attributes[key] = attribute;
                }
                else {
                    groupedAttributes[groupName] = { show:true, attributes: {} };
                    groupedAttributes[groupName].attributes[key] = attribute;
                }
            });

            return groupedAttributes;
        }
    }
})();