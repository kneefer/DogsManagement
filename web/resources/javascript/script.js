/* Main script file */

function editLastDatatableRow() {
            var iloscStron = jQuery('#formPsy').find('.ui-paginator-page').length;
            datatable.paginator.setPage(iloscStron - 1);
            
            jQuery('#formPsy').ajaxStop(function(e, xhr, options) {
                jQuery('#formPsy').find('.ui-datatable-data tr').last().find('span.ui-icon-pencil').each(function() {
                    jQuery(this).click();
                    jQuery(this).parents().eq(2).find('.imie').focus();
                });
                jQuery(e.currentTarget).unbind("ajaxStop");
                
                jQuery('#formPsy').find('.ui-row-editing').find('input').keypress(function(e){
                    var code = null;
                    code = (e.keyCode ? e.keyCode : e.which);
                    if (code == 13){
                        e.preventDefault();
                        jQuery(this).closest('.ui-row-editing').find('.ui-icon-check').click();
                    }
                });
            });
        }