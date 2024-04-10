# Marketplace

This page is not finished!

## Formatting

Monstera tries to remain Marketplace compliant by default, but there are some places where a developer has to configure
things themselves.

### Addons

For addons, Marketplace teams must place entities, blocks or other files in their own subfolders to prevent collisions
with files from other Marketplace teams. You can customize all paths in the monstera.json file for this purpose. For
example:
"behEntity": "entities" -> "behEntity": "entities/partner_name"

### Other

Collisions with other add-ons are not normally a problem, as you can assume that the developed add-on is the only one
that is active.

## Packaging

It is possible to create a zip file using the standard library that is Marketplace compatible. This can still be complex
under certain circumstances, but has the advantage of benefiting from a version control system (such as Git).