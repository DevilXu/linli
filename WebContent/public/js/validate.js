/**
 * 
 */
require.config({
	baseUrl: "public/js/common",
    paths: {
        jquery: 'jquery-1.9.1.min',
        parsley: 'parsley'
    },
    shim: { 
    	parsley : { 
            deps: [ 
                'css!../../css/common/validate.css' 
            ] 
        }
    } 
});
define(['jquery','parsley'], function($) {
	 $('#formvalidate').parsley();
});