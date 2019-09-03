---
# Feel free to add content and custom Front Matter to this file.
# To modify the layout, see https://jekyllrb.com/docs/themes/#overriding-theme-defaults

layout: home
---

This is a collection of posts that provide an introduction to some of the
basic ideas of the role of search in artificial intelligence and machine
learning, ultimately leading to an introduction to evolutionary computation
as a specific type of search tool which can be used in artificial intelligence
and machine learning.

<ol>
{% for page in site.pages %}
  <li><a href="{{ site.baseurl }}{{ page.url }}">{{ page.title }}</a></li>
{% endfor %}
</ol>
